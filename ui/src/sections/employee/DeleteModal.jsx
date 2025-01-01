/* eslint-disable no-unused-vars */
import React from "react";
import { Modal } from "antd";

function showDeleteConfirm({ employeeId, deleteItem }) {
  const confirm = Modal.confirm;
  confirm({
    title: "Delete employee",
    content:
      "Do you really want to delete this employee ? This employee cannot be restored.",
    okText: "Yes",
    okType: "danger",
    cancelText: "No",
    onOk() {
      deleteItem(employeeId);
    },
  });
}

export default showDeleteConfirm;
