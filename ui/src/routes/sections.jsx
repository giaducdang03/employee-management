import { Suspense, lazy } from "react";
import { Outlet, useRoutes, Navigate } from "react-router-dom";
import DashboardLayout from "@/layout";
import { Loading } from "@/components";
import useAuth from "@/hooks/useAuth";
import AuthPage from "../pages/AuthPage/AuthPage";

export const EmployeeManagementView = lazy(() => import("@/pages/EmployeeManagement")) 
export const DepartmentManagementView = lazy(() => import("@/pages/DepartmentManagement")) 

export const Router = () => {
  const { isAuthenticated } = useAuth();

  const routes = useRoutes([
    {
      path: "/",
      element: isAuthenticated ? <Navigate to="/employee" /> : <AuthPage />,
    },
    {
      element: isAuthenticated ? (
        <DashboardLayout>
          <Suspense fallback={<Loading />}>
            <Outlet />
          </Suspense>
        </DashboardLayout>
      ) : (
        <Navigate to="/" />
      ),
      children: [
        {
          element: <EmployeeManagementView />,
          path: "/employee",
        },
        {
          element: <DepartmentManagementView />,
          path: "/department",
        },
      ],
    },
  ]);
  return routes;
};
