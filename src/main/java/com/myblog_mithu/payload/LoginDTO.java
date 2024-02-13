package com.myblog_mithu.payload;


import lombok.Data;


// I want to do Login Feature Normally -
// 1) U should enter username and password in login page.
// 2) U would go to the Database and fetch the username and password.
// 3) and compare it , upon comparing true then its login if not then u can sms like invalid username and password.
// 4)

// 1) I am going for developed Login Feature , where u can Login in 2 ways , u can give username and password Otherwise Ucan give email nad password.
//

//


@Data
public class LoginDTO {

    private String usernameOrEmail;
    private String password;

}

