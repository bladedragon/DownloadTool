package team.redrock.downloadtool.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String status;
    private Object content;


//
//    public static void main(String[] args) {
//        Response response = new Response("0","你好呀");
//        System.out.println(response);
//        return;
//    }
}


