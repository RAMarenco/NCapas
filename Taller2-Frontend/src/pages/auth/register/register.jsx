import { Link, Navigate } from "react-router-dom";
import Input from "./../../../components/input/input";
import Button from "./../../../components/button/button";
import classes from "./register.module.scss";
import { useAuth } from "../../../hooks/useAuth";

const Register = () => {
    const info = {
        email: "",
        username: "",
        password: "",
    };

    const { data, handleOnChange, handleSubmit, isLoggedIn } = useAuth(
        info,
        "register"
    );

    if (isLoggedIn()) {
        return <Navigate to="/dashboard" />;
    } else {
        return (
            <>
                <form
                    action="#"
                    method="post"
                    id="register-form"
                    onSubmit={handleSubmit}
                    className={classes["Register-Form"]}
                >
                    <Input
                        name="email"
                        type="text"
                        value={data.email}
                        label="Email"
                        placeholder="example@mail.com"
                        onChange={(e) => handleOnChange(e)}
                    />
                    <Input
                        name="username"
                        type="text"
                        value={data.username}
                        label="User"
                        placeholder="johndoe"
                        onChange={(e) => handleOnChange(e)}
                    />
                    <Input
                        name="password"
                        type="password"
                        value={data.password}
                        label="Password"
                        placeholder="********"
                        onChange={(e) => handleOnChange(e)}
                    />
                    <Button type="submit">Register</Button>
                </form>
                <p className={classes["Bottom-Text"]}>
                    Already have an account? <Link to="/auth/login">Login</Link>
                </p>
            </>
        );
    }
};

export default Register;
