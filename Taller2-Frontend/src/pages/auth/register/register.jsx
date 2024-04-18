import { useState, useEffect } from "react";
import { toast } from "react-toastify";
import Input from "./../../../components/input/input";
import Button from "./../../../components/button/button";
import classes from "./register.module.scss";

const Register = () => {
    const [data, setData] = useState({
        email: "",
        username: "",
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
        const response = await fetch(
            "http://localhost:8080/api/auth/register",
            {
                method: "POST",
                mode: "cors",
                headers: {
                    "Access-Control-Allow-Origin": "*",
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            }
        );

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
            email: "",
            username: "",
            password: "",
        });
    };

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
                    onChange={(e) => handleOnChange(e)}
                />
                <Input
                    name="username"
                    type="text"
                    value={data.username}
                    label="Username"
                    onChange={(e) => handleOnChange(e)}
                />
                <Input
                    name="password"
                    type="password"
                    value={data.password}
                    label="Password"
                    onChange={(e) => handleOnChange(e)}
                />
                <Button type="submit">Register</Button>
            </form>
        </>
    );
};

export default Register;