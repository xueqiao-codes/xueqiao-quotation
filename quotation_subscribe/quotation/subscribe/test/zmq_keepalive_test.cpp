/*
 * zmq_keepalive_test.cpp
 *
 *  Created on: 2017年9月6日
 *      Author: wileywang
 */

#include <zmq.hpp>


int main(int argc, char* argv[]) {
    zmq::context_t context(1);
    zmq::socket_t sub_socket(context, zmq::socket_type::sub);

//    sub_socket.setsockopt(ZMQ_TCP_KEEPALIVE, 1);
//    sub_socket.setsockopt(ZMQ_TCP_KEEPALIVE_IDLE, 15);
//    sub_socket.setsockopt(ZMQ_TCP_KEEPALIVE_CNT, 3);
//    sub_socket.setsockopt(ZMQ_TCP_KEEPALIVE_INTVL, 5);
    sub_socket.setsockopt(ZMQ_HEARTBEAT_IVL, 15000);
    sub_socket.setsockopt(ZMQ_HEARTBEAT_TIMEOUT, 3000);
    sub_socket.setsockopt(ZMQ_HEARTBEAT_TTL, 60000);

    sub_socket.connect("tcp://172.17.225.28:1556");

    sub_socket.setsockopt(ZMQ_SUBSCRIBE, "notopic", 7);

    while(true) {
        zmq::message_t msg;
        if (!sub_socket.recv(&msg)) {
            continue;
        }
    }

    return 0;
}


