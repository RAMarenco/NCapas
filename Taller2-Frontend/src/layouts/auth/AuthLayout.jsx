import { Outlet } from "react-router-dom"
import { ToastContainer } from "react-toastify"
import classes from './AuthLayout.module.scss'
import './../reset.css'
import 'react-toastify/dist/ReactToastify.css'

const AuthLayout = () => {
    return (
        <>
            <ToastContainer/>
            <div className={classes["Auth-Container"]}>
                <div className={classes["Form-Container"]}>
                    <Outlet/>
                </div>
            </div>
        </>
    )
}

export default AuthLayout