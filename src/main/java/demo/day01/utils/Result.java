package demo.day01.utils;

import lombok.Data;
import lombok.Setter;

@Data
public class Result<T> {
    @Setter
    private String code;
    private String msg;
    private T data;

    public Result() {

    }

    public Result(T data) {
        this.data = data;
    }

    public static Result success() {
        Result result = new Result<>();
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    public static <T> Result<T> success(T data, String msg) {
        Result<T> result = new Result<>(data);
        result.setCode("200");
        result.setMsg(msg);
        return result;
    }

//    public static <T> Result<T> successtset(T data,String msg) {
//
//        Result<T> result = new Result<>(data);
//        result.setCode("200");
//    }

    public static Result error( String code,String msg ) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
