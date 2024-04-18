import { Navigate } from "react-router-dom";
import AuthLayout from "../layouts/auth/AuthLayout";
import Login from "./../pages/auth/login/login";
import Register from "./../pages/auth/register/register";
import NotFound from "../pages/notFound/NotFound";
import { ProtectedRoute } from "../components/ProtectedRoute";

const Routes = [
    {
        path: "/",
    },
    {
        path: "/auth",
        element: <Navigate to="/auth/login" replace />,
    },
    {
        path: "/auth",
        element: <AuthLayout />,
        children: [
            {
                path: "register",
                element: <Register />,
            },
            {
                path: "login",
                element: <Login />,
            },
        ],
    },
    {
        element: <ProtectedRoute />,
        children: [
            {
                path: "/dashboard",
                element: <>Dashboard</>,
            },
        ],
    },
    {
        path: "*",
        element: <NotFound />,
    },
];

export default Routes;
