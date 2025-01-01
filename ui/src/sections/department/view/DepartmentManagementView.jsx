/* eslint-disable no-unused-vars */
import React from "react";
import DepartmentList from "../DepartmentList";
import Title from "../Title";


function DepartmentManagementView() {
  return (
    <div className="p-5">
      <Title />
      <DepartmentList />
    </div>
  );
}

export default DepartmentManagementView;
