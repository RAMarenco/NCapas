import { useAuth } from "../../../hooks/useAuth";
import { Link, Navigate } from "react-router-dom";
import Input from "./../../../components/input/input";
import Button from "./../../../components/button/button";
import classes from "./login.module.scss";

const Login = () => {
    const info = {
        identifier: "",
        password: "",
    };

    const { data, handleOnChange, handleSubmit, isLoggedIn } = useAuth(
        info,
        "login"
    );

    if (isLoggedIn()) {
        return <Navigate to="/dashboard" />;
    } else {
        return (
            <>
                <form
                    action="#"
                    method="post"
                    id="login-form"
                    onSubmit={handleSubmit}
                    className={classes["Login-Form"]}
                >
                    <Input
                        name="identifier"
                        type="text"
                        label="Identifier"
                        placeholder="Type your email or username"
                        value={data.identifier}
                        onChange={(e) => handleOnChange(e)}
                    />
                    <Input
                        name="password"
                        type="password"
                        label="Password"
                        placeholder="********"
                        value={data.password}
                        onChange={(e) => handleOnChange(e)}
                    />
                    <Button type="submit">Login</Button>
                </form>
                <p className={classes["Bottom-Text"]}>
                    Don't have an account?{" "}
                    <Link to="/auth/register">Register</Link>
                </p>
            </>
        );
    }
};

export default Login;
