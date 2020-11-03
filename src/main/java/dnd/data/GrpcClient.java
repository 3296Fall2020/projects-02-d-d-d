package dnd.data;

import dnd.generated.UserDataGrpc;
import dnd.generated.UserOuterClass;
import io.grpc.Channel;
import io.grpc.StatusRuntimeException;

public class GrpcClient {

    private final UserDataGrpc.UserDataBlockingStub blockingStub;

    public GrpcClient(Channel channel) {
        // 'channel' here is a Channel, not a ManagedChannel, so it is not this code's responsibility to
        // shut it down.

        // Passing Channels to code makes code easier to test and makes it easier to reuse Channels.
        blockingStub = UserDataGrpc.newBlockingStub(channel);
    }

    public void CreateUser(String name) {
        UserOuterClass.User usr = UserOuterClass.User.newBuilder().setName(name).build();
        UserOuterClass.CreateUserRequest req = UserOuterClass.CreateUserRequest.newBuilder().setUser(usr).build();
        UserOuterClass.CreateUserResponse response;
        try {
            response = blockingStub.createUser(req);
        } catch (StatusRuntimeException e) {
            System.out.println(e.getStatus());
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Create Success: " + response.getSuccess());
    }



}
