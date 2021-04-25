package test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

/**
 * Created by leibiao on 2017/3/6.
 */
public class HBaseDemo {

    private static final String TABLE_NAME = "mytable";
    private static final String CF_DEFAULT = "cf";
    public static final byte[] QUALIFIER = "col1".getBytes();
    private static final byte[] ROWKEY = "rowkey1".getBytes();

    public static void main(String[] args) {
        Configuration config = HBaseConfiguration.create();
        String zkAddress = "hb-uf60w9m153x9x3159-004.hbase.rds.aliyuncs.com:2181,hb-uf60w9m153x9x3159-003.hbase.rds.aliyuncs.com:2181,hb-uf60w9m153x9x3159-001.hbase.rds.aliyuncs.com:2181";
        config.set(HConstants.ZOOKEEPER_QUORUM, zkAddress);
        Connection connection = null;

        try {
            connection = ConnectionFactory.createConnection(config);

//            HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(TABLE_NAME));
//            tableDescriptor.addFamily(new HColumnDescriptor(CF_DEFAULT));
//            System.out.print("Creating table. ");
//            Admin admin = connection.getAdmin();
//            admin.createTable(tableDescriptor);
//            System.out.println(" Done.");
            Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
            
            try {
            	for (int index = 0; index < 3; ++index) {
            		Put put = new Put(ROWKEY);
            		put.addColumn(CF_DEFAULT.getBytes(), QUALIFIER, ("this is value" + index).getBytes());
            		table.put(put);
            	}
            	
            	Get get = new Get(ROWKEY);
            	get.setMaxVersions();
        		Result r = table.get(get);
        		
        		List<Cell> cells = r.getColumnCells(CF_DEFAULT.getBytes(), QUALIFIER);
        		System.out.println("cell count=" + cells.size());
        		for (Cell cell : cells) {
        			System.out.println("ts=" + cell.getTimestamp() + ", value=" + new String(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
        		}
//        		byte[] b = r.getValue(CF_DEFAULT.getBytes(), QUALIFIER);  // returns current version of value
//        		System.out.println(new String(b));
            } finally {
                if (table != null) table.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
