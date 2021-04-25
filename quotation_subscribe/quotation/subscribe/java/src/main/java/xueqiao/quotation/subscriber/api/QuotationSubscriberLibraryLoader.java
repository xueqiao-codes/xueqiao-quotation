package xueqiao.quotation.subscriber.api;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class QuotationSubscriberLibraryLoader {
private static boolean isInited = false;
	
	public static void init() {
		if (!isInited) {
			synchronized(QuotationSubscriberLibraryLoader.class) {
				if (!isInited) {
					try {
						loadLib();
						isInited = true;
					} catch (IOException e) {
						e.printStackTrace();
						Runtime.getRuntime().exit(1);
					}
				}
			}
		}
	}
	
	private static void loadLib() throws IOException 
    {  
        String libFullName = "libQuotationSubscriber_java.so";  
        InputStream in = null;  
        BufferedInputStream reader = null;  
        FileOutputStream writer = null;  

        File extractedLibFile = File.createTempFile("libQuotationSubscriber_java",".so"); 
        try { 
            in = QuotationSubscriberLibraryLoader.class.getResourceAsStream("/linux/" + libFullName);  
            reader = new BufferedInputStream(in);  
            writer = new FileOutputStream(extractedLibFile);  
            byte[] buffer = new byte[1024];  
            while (reader.read(buffer) > 0){  
                writer.write(buffer);  
                buffer = new byte[1024];
            }  
            writer.flush();
        } catch (IOException e){  
            e.printStackTrace();  
        } finally {  
            if(in!=null)  
                in.close();  
            if(writer!=null)  
                writer.close();  
        }
        System.out.println("loading " + extractedLibFile.toString());
        System.load(extractedLibFile.toString());  
        if (extractedLibFile.exists()) {
            extractedLibFile.delete();
        }
    }  
}
