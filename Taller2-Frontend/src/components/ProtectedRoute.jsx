import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "./../hooks/useAuth";

export const ProtectedRoute = ({ children, redirectTo = "/" }) => {
    const { dataStorage } = useAuth();
    if (dataStorage() === null) {
        return <Navigate to={redirectTo} />;
    }

    return children ? children : <Outlet />;
};
