/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from "react";
import { Modal, Form, Input, Row, Col, Select, DatePicker } from "antd";
import { PhoneOutlined, MailOutlined, UserOutlined, DollarOutlined, HomeOutlined } from "@ant-design/icons";
import dayjs from "dayjs";
import moment from "moment/moment";
import useEmployeeStore from "@/hooks/useEmployeeStore";
import { formatDate } from "@/utils/dateUtils";
import { provinces, roles } from "@/shared/data";
import useDepartmentStore from "../../hooks/useDepartmentStore";

const EditModal = ({ setIsOpen, isOpen, employeeInfo }) => {
  const [confirmLoading, setConfirmLoading] = useState(false);
  const { updateItem, isFetching } = useEmployeeStore();
  const { departments } = useDepartmentStore(1, 50);
  const [selectedDepartmentId, setSelectedDepartmentId] = useState(0);
  const { Option } = Select;
  const [form] = Form.useForm();

  useEffect(() => {
    if (isOpen) {
      setSelectedDepartmentId(employeeInfo.departmentId);
      const updatedEmployeeInfo = { ...employeeInfo };
      if (updatedEmployeeInfo.dateOfBirth) {
        updatedEmployeeInfo.dateOfBirth = dayjs(
          updatedEmployeeInfo.dateOfBirth
        );
      }
      if (updatedEmployeeInfo.hireDate) {
        updatedEmployeeInfo.hireDate = dayjs(updatedEmployeeInfo.hireDate);
      }
      form.setFieldsValue(updatedEmployeeInfo);
    }
  }, [isOpen]);

  const handleOk = async () => {
    try {
      const values = await form.validateFields();
      const formattedDobDate = formatDate(values.dateOfBirth);
      const formattedHireDate = formatDate(values.hireDate);
      const updatedValues = {
        ...values,
        id: employeeInfo.id,
        dateOfBirth: formattedDobDate,
        hireDate: formattedHireDate,
        departmentId: selectedDepartmentId,
      };
      setConfirmLoading(true);
      setTimeout(async () => {
        try {
          console.log("check value form", updatedValues);
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

  const handleDepartmentChange = (value) => {
    setSelectedDepartmentId(value);
  };

  const disabledDate = (current) => {
    return current && current > moment().startOf("day");
  };

  return (
    <Modal
      title={<p className="text-[red] text-lg">Employee infomation</p>}
      open={isOpen}
      onOk={handleOk}
      confirmLoading={confirmLoading}
      onCancel={handleCancel}
    >
      <Form name="normal_login" className="login-form" form={form}>
        <Form.Item
          name="email"
          rules={[
            {
              required: true,
              message: "Please input email",
            },
            {
              type: "email",
              message: "Please enter a valid email address",
            },
          ]}
          colon={true}
          label="Email"
          labelCol={{ span: 24 }}
          className="mt-2"
        >
          <Input
            prefix={<MailOutlined className="site-form-item-icon" />}
            placeholder="Email"
          />
        </Form.Item>
        <Row gutter={16} className="relative">
          <Col span={12}>
            <Form.Item
              name="fullName"
              rules={[
                {
                  required: true,
                  message: "Please input name",
                },
                {
                  min: 5,
                  message: "Name must be at least 5 characters",
                },
              ]}
              colon={true}
              label="Name"
              labelCol={{ span: 24 }}
              className="absolute"
            >
              <Input
                prefix={<UserOutlined className="site-form-item-icon" />}
                placeholder="Name"
                autoFocus
              />
            </Form.Item>
          </Col>
          <Col span={9}>
            <Form.Item
              name="dateOfBirth"
              rules={[
                {
                  required: true,
                  message: "Please input dob",
                },
              ]}
              colon={true}
              label="Date of birth"
              labelCol={{ span: 24 }}
            >
              <DatePicker
                picker="date"
                disabledDate={disabledDate}
                format="YYYY-MM-DD"
                className="w-full"
              />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16} className="relative mt-1">
          <Col span={12}>
            <Form.Item
              name="phoneNumber"
              rules={[
                {
                  required: true,
                  message: "Please input phone",
                },
                {
                  pattern: /^\d{10}$/,
                  message: "Phone must be exactly 10 digits",
                },
              ]}
              colon={true}
              label="Phone"
              labelCol={{ span: 24 }}
              className="absolute"
            >
              <Input
                prefix={
                  <PhoneOutlined className="site-form-item-icon rotate-90" />
                }
                placeholder="Phone"
                maxLength={10}
              />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item
              name="jobTitle"
              rules={[
                {
                  required: true,
                  message: "Please input job title",
                },
              ]}
              colon={true}
              label="Job title"
              labelCol={{ span: 24 }}
            >
              <Select placeholder="Select job title">
                {roles.map((role) => (
                  <Select.Option key={role} value={role}>
                    {role}
                  </Select.Option>
                ))}
              </Select>
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={16} className="relative mt-1">
          <Col span={12}>
            <Form.Item
              name="departmentName"
              rules={[
                {
                  required: true,
                  message: "Please select department",
                },
              ]}
              colon={true}
              label="Department"
              labelCol={{ span: 24 }}
              className="mt-2"
            >
              <Select
                placeholder="Select a department"
                allowClear
                autoFocus
                prefix={<MailOutlined className="site-form-item-icon" />}
                onChange={handleDepartmentChange}
              >
                {departments?.content?.length > 0 ? (
                  departments.content.map((department) => (
                    <Option key={department.id} value={department.id}>
                      {department.name}
                    </Option>
                  ))
                ) : (
                  <Option disabled value="no-department">
                    No departments available
                  </Option>
                )}
              </Select>
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item
              name="baseSalary"
              rules={[
                {
                  required: true,
                  message: "Please input base salary",
                },
              ]}
              colon={true}
              label="Base salary"
              labelCol={{ span: 24 }}
              className="mt-2"
            >
              <Input
                prefix={<DollarOutlined className="site-form-item-icon" />}
                placeholder="Base salary"
                autoFocus
              />
            </Form.Item>
          </Col>
        </Row>

        <Form.Item
          name="hireDate"
          rules={[
            {
              required: true,
              message: "Please input hired date",
            },
          ]}
          colon={true}
          label="Hired date"
          labelCol={{ span: 24 }}
        >
          <DatePicker
            picker="date"
            disabledDate={disabledDate}
            format="YYYY-MM-DD"
            className="w-full"
          />
        </Form.Item>
        <Form.Item
          name="address"
          rules={[
            {
              required: true,
              message: "Please input address",
            },
          ]}
          colon={true}
          label="Address"
          labelCol={{ span: 24 }}
        >
          <Input
            prefix={<HomeOutlined className="site-form-item-icon" />}
            placeholder="Address"
          />
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default EditModal;