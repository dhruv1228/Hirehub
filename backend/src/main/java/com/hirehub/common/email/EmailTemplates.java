package com.hirehub.common.email;

public class EmailTemplates {

    public static String welcome(String name) {

        return """
        <!DOCTYPE html>
        <html>
        <body style="font-family:Arial;background:#f5f5f5;padding:40px;">

        <div style="
            max-width:600px;
            margin:auto;
            background:white;
            border-radius:10px;
            padding:30px;
            box-shadow:0 0 15px rgba(0,0,0,.1);
        ">

            <h1 style="color:#2563eb;">
                Welcome to HireHub 🎉
            </h1>

            <p>Hello <b>%s</b>,</p>

            <p>
                Your HireHub account has been created successfully.
            </p>

            <p>
                We wish you all the best in your job search.
            </p>

            <hr>

            <small>
                © 2026 HireHub
            </small>

        </div>

        </body>
        </html>
        """.formatted(name);
    }

}