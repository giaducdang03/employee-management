/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */
import useDepartmentStore from "@/hooks/useDepartmentStore";
import { formatDate } from "@/utils/dateUtils";
import { CopyOutlined, DollarOutlined, FormOutlined, HomeOutlined, MailOutlined, PhoneOutlined, UserOutlined } from "@ant-design/icons";
import { Col, DatePicker, Form, Input, Modal, Row, Select } from "antd";
import moment from "moment/moment";
import React, { useState } from "react";
import { roles } from "../../shared/data";

const AddModal = ({ setIsOpen, isOpen }) => {
  const [confirmLoading, setConfirmLoading] = useState(false);
  const { addItem, isFetching } = useDepartmentStore();
  const { Option } = Select;
  const [form] = Form.useForm();
  const { departments } = useDepartmentStore(1, 50);
  const [selectedDepartmentId, setSelectedDepartmentId] = useState(0);

  const handleOk = async () => {
    try {
      const values = await form.validateFields();
      setConfirmLoading(true);
      setTimeout(async () => {
        try {
          await addItem(values);
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
    form.resetFields();
  };

  return (
    <Modal
      title={<p className="text-[red] text-lg">Add new department</p>}
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
            }
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
            }
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

export default AddModal;
