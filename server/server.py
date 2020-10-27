from generated import user_pb2 as pb_user
from generated import user_pb2_grpc as pb_user_server
from data.user_data_servicer import UserDataService

from concurrent import futures
import logging

import grpc




if __name__ == '__main__':
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=8))
    pb_user_server.add_UserDataServicer_to_server(UserDataService(), server)
    server.add_insecure_port('[::]:50051')
    server.start()
    server.wait_for_termination()
