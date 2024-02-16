package uz.pdp.clickuzusers.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Response {
    private String message;
    private LocalDateTime time = LocalDateTime.now();
    public Response(){
        this.message = "Successfully";
    }
    public Response(String message){
        this.message = message;
    }
}

