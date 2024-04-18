import { useState } from "react";
import { toast } from "react-toastify";

export const useAuth = (info = {}, page = "") => {
    const [data, setData] = useState(info);

    const handleOnChange = (e) => {
        const { name, value } = e.target;
        setData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const response = await fetch(`http://localhost:8080/api/auth/${page}`, {
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

        setData(info);
    };

    const dataStorage = () => {
        return JSON.parse(localStorage.getItem("dataStorage")) || null;
    };

    const isLoggedIn = () => {
        return dataStorage() != null;
    };

    return { dataStorage, data, handleOnChange, handleSubmit, isLoggedIn };
};
