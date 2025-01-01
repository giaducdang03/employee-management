/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */
import useDepartmentStore from "@/hooks/useDepartmentStore";
import { CopyOutlined, FormOutlined } from "@ant-design/icons";
import { Form, Input, Modal, Select } from "antd";
import React, { useEffect, useState } from "react";

const EditModal = ({ setIsOpen, isOpen, departmentInfo }) => {
  const [confirmLoading, setConfirmLoading] = useState(false);
  const { updateItem, isFetching } = useDepartmentStore();
  const { departments } = useDepartmentStore(1, 50);
  const [selectedDepartmentId, setSelectedDepartmentId] = useState(0);
  const { Option } = Select;
  const [form] = Form.useForm();

    useEffect(() => {
      if (isOpen) {
        form.setFieldsValue(departmentInfo);
      }
    }, [isOpen]);

  const handleOk = async () => {
    try {
      const values = await form.validateFields();
      const updatedValues = {
        ...values,
        id: departmentInfo.id,
      };
      setConfirmLoading(true);
      setTimeout(async () => {
        try {
          await updateItem(updatedValues);
          setConfirmLoading(false);
          setIsOpen(false);
        } catch (error) {
          setConfirmLoading(false);
          setIsOpen(true);
        }
      }, isFetching);
    } catch (errorInfo) {
      console.log("Validation failed:", errorInfo);
    }
  };

  const handleCancel = () => {
    setIsOpen(false);
  };

  return (
    <Modal
      title={<p className="text-[red] text-lg">Department infomation</p>}
      open={isOpen}
      onOk={handleOk}
      confirmLoading={confirmLoading}
      onCancel={handleCancel}
    >
      <Form name="normal_login" className="login-form" form={form}>
        <Form.Item
          name="name"
          rules={[
            {
              required: true,
              message: "Please input name",
            },
          ]}
          colon={true}
          label="Name"
          labelCol={{ span: 24 }}
          className="mt-2"
        >
          <Input
            prefix={<CopyOutlined className="site-form-item-icon" />}
            placeholder="Name"
          />
        </Form.Item>
        <Form.Item
          name="description"
          rules={[
            {
              required: true,
              message: "Please input description",
            },
          ]}
          colon={true}
          label="Description"
          labelCol={{ span: 24 }}
          className="mt-2"
        >
          <Input
            prefix={<FormOutlined className="site-form-item-icon" />}
            placeholder="Description"
          />
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default EditModal;
