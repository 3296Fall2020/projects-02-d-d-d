import sys

sys.path.append('../')

from generated import user_pb2_grpc
from generated import user_pb2


class UserDataService(user_pb2_grpc.UserDataServicer):

    def CreateUser(self, request, context):
        return user_pb2.CreateUserResponse(success=True)
