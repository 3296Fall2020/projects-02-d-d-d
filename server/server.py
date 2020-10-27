import server.generated.user_pb2 as pb_user
import server.generated.user_pb2_grpc as pb_user_server

from concurrent import futures
import logging

import grpc




if __name__ == '__main__':
    pb_user.User()
    pb_user_server.add_UserDataServicer_to_server()