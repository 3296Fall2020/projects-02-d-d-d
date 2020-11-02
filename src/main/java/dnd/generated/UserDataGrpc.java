package dnd.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**

@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.0)",
    comments = "Source: protobuf/user.proto") */
public final class UserDataGrpc {

  private UserDataGrpc() {}

  public static final String SERVICE_NAME = "UserData";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<UserOuterClass.CreateUserRequest,
      UserOuterClass.CreateUserResponse> getCreateUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateUser",
      requestType = UserOuterClass.CreateUserRequest.class,
      responseType = UserOuterClass.CreateUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<UserOuterClass.CreateUserRequest,
      UserOuterClass.CreateUserResponse> getCreateUserMethod() {
    io.grpc.MethodDescriptor<UserOuterClass.CreateUserRequest, UserOuterClass.CreateUserResponse> getCreateUserMethod;
    if ((getCreateUserMethod = UserDataGrpc.getCreateUserMethod) == null) {
      synchronized (UserDataGrpc.class) {
        if ((getCreateUserMethod = UserDataGrpc.getCreateUserMethod) == null) {
          UserDataGrpc.getCreateUserMethod = getCreateUserMethod =
              io.grpc.MethodDescriptor.<UserOuterClass.CreateUserRequest, UserOuterClass.CreateUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UserOuterClass.CreateUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UserOuterClass.CreateUserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserDataMethodDescriptorSupplier("CreateUser"))
              .build();
        }
      }
    }
    return getCreateUserMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserDataStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserDataStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserDataStub>() {
        @java.lang.Override
        public UserDataStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserDataStub(channel, callOptions);
        }
      };
    return UserDataStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserDataBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserDataBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserDataBlockingStub>() {
        @java.lang.Override
        public UserDataBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserDataBlockingStub(channel, callOptions);
        }
      };
    return UserDataBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserDataFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserDataFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserDataFutureStub>() {
        @java.lang.Override
        public UserDataFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserDataFutureStub(channel, callOptions);
        }
      };
    return UserDataFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class UserDataImplBase implements io.grpc.BindableService {

    /**
     */
    public void createUser(UserOuterClass.CreateUserRequest request,
        io.grpc.stub.StreamObserver<UserOuterClass.CreateUserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateUserMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                UserOuterClass.CreateUserRequest,
                UserOuterClass.CreateUserResponse>(
                  this, METHODID_CREATE_USER)))
          .build();
    }
  }

  /**
   */
  public static final class UserDataStub extends io.grpc.stub.AbstractAsyncStub<UserDataStub> {
    private UserDataStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserDataStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserDataStub(channel, callOptions);
    }

    /**
     */
    public void createUser(UserOuterClass.CreateUserRequest request,
        io.grpc.stub.StreamObserver<UserOuterClass.CreateUserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserDataBlockingStub extends io.grpc.stub.AbstractBlockingStub<UserDataBlockingStub> {
    private UserDataBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserDataBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserDataBlockingStub(channel, callOptions);
    }

    /**
     */
    public UserOuterClass.CreateUserResponse createUser(UserOuterClass.CreateUserRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateUserMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserDataFutureStub extends io.grpc.stub.AbstractFutureStub<UserDataFutureStub> {
    private UserDataFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserDataFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserDataFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<UserOuterClass.CreateUserResponse> createUser(
        UserOuterClass.CreateUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_USER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserDataImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserDataImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_USER:
          serviceImpl.createUser((UserOuterClass.CreateUserRequest) request,
              (io.grpc.stub.StreamObserver<UserOuterClass.CreateUserResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UserDataBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserDataBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return UserOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserData");
    }
  }

  private static final class UserDataFileDescriptorSupplier
      extends UserDataBaseDescriptorSupplier {
    UserDataFileDescriptorSupplier() {}
  }

  private static final class UserDataMethodDescriptorSupplier
      extends UserDataBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserDataMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UserDataGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserDataFileDescriptorSupplier())
              .addMethod(getCreateUserMethod())
              .build();
        }
      }
    }
    return result;
  }
}
