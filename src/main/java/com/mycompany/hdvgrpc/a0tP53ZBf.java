package com.mycompany.hdvgrpc;
import GRPC.*;
import io.grpc.*;
import java.util.*;
public class a0tP53ZBf {

    public static void main(String args[]) {
        String studentCode = "B22DCCN141"; 
        String alias = "0tP53ZBf"; 
        String host = "36.50.135.242"; // đổi thành IP server
        // =====================

        // Kết nối gRPC plaintext
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, 2240)
                .usePlaintext()
                .build();

        // Tạo stub
        JudgeServiceGrpc.JudgeServiceBlockingStub stub =
                JudgeServiceGrpc.newBlockingStub(channel);

        // ===== Request =====
        JudgeRequest request = JudgeRequest.newBuilder()
                .setStudentCode(studentCode)
                .setQuestionAlias(alias)
                .build();

        JudgeResponse response = stub.request(request);

        System.out.println("Request ID: " + response.getRequestId());
        System.out.println("Data: " + response.getData());

        // ===== Xử lý dữ liệu =====
        String[] arr = response.getData().split(",");

        int sum = 0;

        for (String s : arr) {
            sum += Integer.parseInt(s.trim());
        }

        String answer = String.valueOf(sum);

        System.out.println("Answer: " + answer);

        // ===== Submit =====
        SubmitRequest submitRequest = SubmitRequest.newBuilder()
                .setStudentCode(studentCode)
                .setQuestionAlias(alias)
                .setRequestId(response.getRequestId())
                .setAnswer(answer)
                .build();

        SubmitResponse submitResponse = stub.submit(submitRequest);

        System.out.println("Status: " + submitResponse.getStatus());
        System.out.println("Message: " + submitResponse.getMessage());

        channel.shutdown();
    }
}
