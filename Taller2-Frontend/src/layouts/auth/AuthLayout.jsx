import { Outlet, useLocation } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import classes from "./AuthLayout.module.scss";
import "./../reset.css";
import "react-toastify/dist/ReactToastify.css";

const AuthLayout = () => {
    const location = useLocation();

    return (
        <>
            <ToastContainer />
            <div className={classes["Auth-Container"]}>
                <div className={classes["Form-Container"]}>
                    <h1 className={classes["Title-Text"]}>
                        {location.pathname === "/auth/login"
                            ? "Login"
                            : "Register"}
                    </h1>
                    <Outlet />
                </div>
            </div>
        </>
    );
};

export default AuthLayout;
