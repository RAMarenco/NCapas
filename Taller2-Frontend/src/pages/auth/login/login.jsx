import { useState } from "react";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import Input from "./../../../components/input/input";
import Button from "./../../../components/button/button";
import classes from "./login.module.scss";

const Login = () => {
    const [data, setData] = useState({
        identifier: "",
        password: "",
    });

    const handleOnChange = (e) => {
        const { name, value } = e.target;
        setData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const response = await fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            mode: "cors",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data),
        });

        const result = await response.json();

        if (!response.ok) {
            toast.warn(`${response.status} ${result.message}`, {
                position: "bottom-right",
                autoClose: 5000,
                closeOnClick: true,
                pauseOnHover: false,
                draggable: false,
                theme: "dark",
            });
            return;
        }

        toast.success(`${result.message}`, {
            position: "bottom-right",
            autoClose: 5000,
            closeOnClick: true,
            pauseOnHover: false,
            draggable: false,
            theme: "dark",
        });

        setData({
            identifier: "",
            password: "",
        });
    };

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
            <p className={classes["Bottom-Text"]}>Don't have an account? <Link to="/auth/register">Login</Link></p>
        </>
    );
};

export default Login;
