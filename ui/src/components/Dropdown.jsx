/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */
import showDeleteConfirm from "@/sections/employee/DeleteModal";
import { DeleteOutlined, MoreOutlined } from "@ant-design/icons";
import { Dropdown } from "antd";
import PropTypes from "prop-types";
import React, { useState } from "react";
import { Link } from "react-router-dom";
import useEmployeeStore from "../hooks/useEmployeeStore";
import EditModal from "../sections/employee/EditModal";

const DropdownBox = ({ employeeId, employeeInfo }) => {
  const { deleteItem } = useEmployeeStore();
  const [isOpen, setIsOpen] = useState(false);

  const openEditModal = () => {
    setIsOpen(true);
  };

  return (
    <>
      <Dropdown
        menu={{
          items: [
            {
              key: "2",
              label: (
                <Link
                  rel="noopener noreferrer"
                  href="#"
                  onClick={() => showDeleteConfirm({ employeeId, deleteItem })}
                >
                  <DeleteOutlined className="pr-2" />
                  Delete Employee
                </Link>
              ),
            },
          ],
        }}
        trigger={["click"]}
      >
        <MoreOutlined className="rotate-90" />
      </Dropdown>

      <EditModal isOpen={isOpen} setIsOpen={setIsOpen} employeeInfo={employeeInfo} />
    </>
  );
};

Dropdown.propTypes = {
  classId: PropTypes.number,
  userInfo: PropTypes.object,
};

export default DropdownBox;
