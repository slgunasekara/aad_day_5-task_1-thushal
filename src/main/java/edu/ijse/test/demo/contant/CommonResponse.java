package edu.ijse.test.demo.contant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {

    private int status;
    private String message;
    private Object body;

    public CommonResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
