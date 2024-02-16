package uz.pdp.clickuzusers.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ClickUzAuthentication {
    private Object principal;
    private Object credentials;
    private List<String> roles;
}
