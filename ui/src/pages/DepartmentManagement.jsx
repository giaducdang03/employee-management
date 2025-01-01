/* eslint-disable no-unused-vars */
import React from "react";
import { Helmet } from "react-helmet";
import { DepartmentManagementView } from "../sections/department/view";

function DepartmentManagement() {
  return (
    <div>
      <Helmet>
        <title> EMS | Quản lý nhân viên </title>
      </Helmet>
      <DepartmentManagementView />
    </div>
  );
}

export default DepartmentManagement;
