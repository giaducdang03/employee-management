/* eslint-disable no-unused-vars */
import useDepartmentStore from "@/hooks/useDepartmentStore";
import { FilterOutlined } from "@ant-design/icons";
import { Button, Input, Table } from "antd";
import React, { useDeferredValue, useEffect, useMemo, useState } from "react";
import { formatTimestampWithHour } from "../../utils/dateUtils";
import AddModal from "./AddModal";
import EditModal from "./EditModal";

const DepartmentList = () => {
  const [currentPage, setCurrentPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [isOpenEdit, setIsOpenEdit] = useState(false);
  const [searchValue, setSearchValue] = useState("");
  const [departmentId, setDepartmentId] = useState();
  const [departmentInfo, setDepartmentInfo] = useState({});
  const [isOpen, setIsOpen] = useState(false);
  const [filterData, setFilterData] = useState([]);
  const [sortOrder, setSortOrder] = useState("asc");
  const deferredSearchValue = useDeferredValue(searchValue, {
    timeoutMs: 1000,
  });

  const openDetailModel = () => {
    setIsOpenEdit(true);
  };

  const { departments, totalCount, isFetching } = useDepartmentStore(
    currentPage,
    pageSize
  );

  const columns = [
    {
      title: "STT",
      dataIndex: "index",
      width: "10%",
      key: "index",
      render: (_, _record, index) => index + 1,
    },
    {
      title: "Name",
      dataIndex: "name",
      width: "20%",
      className: "first-column",
      render: (text, record) => (
        <a
          onClick={(e) => {
            e.stopPropagation();
            handleRowClick(record);
          }}
        >
          {text}
        </a>
      ),
    },
    {
      title: "Description",
      dataIndex: "description",
      width: "20%",
    },
    {
      title: "Created date",
      dataIndex: "createdDate",
      width: "15%",
      render: (createDate) => {
        return formatTimestampWithHour(createDate);
      },
    },
    {
      title: "Updated date",
      dataIndex: "updatedDate",
      width: "15%",
      render: (updatedDate) => {
        return formatTimestampWithHour(updatedDate);
      },
    }
  ];

  const [tableParams, setTableParams] = useState({
    pagination: {
      current: 1,
      pageSize: 10,
    },
  });

  useEffect(() => {
    if (!departments || departments.length === 0) {
      return;
    }
    handleSearch(deferredSearchValue);
  }, [deferredSearchValue, departments]);

  const handleSearch = useMemo(() => {
    return (value) => {
      if (!departments.content) {
        console.error("Department data is undefined or empty");
        return;
      }
      const searchData = departments.content.filter((department) => {
        return department?.name.toLowerCase().includes(value.toLowerCase());
      });
      setFilterData(searchData.length > 0 ? searchData : []);
    };
  }, [departments]);

  const handleSort = () => {
    let sortedData = [...filterData];

    if (sortedData.length > 0) {
      sortedData = sortedData.sort((a, b) =>
        sortOrder.includes("asc")
          ? a.name.localeCompare(b.name)
          : b.name.localeCompare(a.name)
      );

      setSortOrder(sortOrder.includes("asc") ? "desc" : "asc");
      setFilterData(sortedData);
    }
  };

  const handleTableChange = (pagination, filters, sorter) => {
    setCurrentPage(pagination.current || 1);
    setPageSize(pagination.pageSize || 10);
    setTableParams({
      pagination,
      filters,
      ...sorter,
    });
  };

  const handleRowClick = (value) => {
    setDepartmentId(value.id);
    setDepartmentInfo(value);
    openDetailModel();
  };

  return (
    <>
      <div>
        <div className="flex justify-between">
          <div className="flex gap-x-2">
            <Input
              placeholder="Search by..."
              className="sm:mb-5 max-w-lg sm:w-[300px] h-8 rounded-lg"
              onChange={(e) => setSearchValue(e.target.value)}
              data-testid="inputSearch"
            />
            <Button
              className="flex items-center"
              onClick={handleSort}
              type="primary"
            >
              <FilterOutlined className="align-middle" />
              Sort
            </Button>
          </div>
          <div className="flex gap-x-2">
            <div>{/* <ExportButton /> */}</div>
            <div>
              <Button type="primary" onClick={() => setIsOpen(true)}>
                + Add
              </Button>
            </div>
          </div>
        </div>
      </div>
      <Table
        id="myTable"
        columns={columns}
        dataSource={filterData}
        pagination={{
          current: currentPage,
          total: totalCount || 0,
          pageSize: pageSize,
        }}
        loading={isFetching}
        onChange={handleTableChange}
      />
      <EditModal
        setIsOpen={setIsOpenEdit}
        isOpen={isOpenEdit}
        departmentInfo={departmentInfo}
      />
      <AddModal setIsOpen={setIsOpen} isOpen={isOpen} />
    </>
  );
};

export default DepartmentList;
