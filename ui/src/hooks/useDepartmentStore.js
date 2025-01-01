import { useMutation, useQuery, useQueryClient } from "react-query";
import { addNewDepartment, editDepartment, getAllDepartments } from "../api/departmentApi";
import { notification } from "antd";

const useDepartmentStore = (page, size) => {
  const queryClient = useQueryClient();

  const fetchDepartments = async ({ queryKey }) => {
    const [, page, size] = queryKey;
    const res = await getAllDepartments(page, size);
    return {
      departments: res.data.data,
      totalCount: res.data.totalCount,
    };
  };

  const updateDepartment = async ({ updatedItem }) => {
    await editDepartment( updatedItem);
  };

  const addDepartment = async (addItem) => {
    await addNewDepartment(addItem);
  };

  // const deleteDepartment = async (itemId) => {
  //   await removeDepartment(itemId);
  //   return itemId;
  // };

  const {
    data: { departments = [], totalCount = 0 } = {},
    isLoading: isFetching,
  } = useQuery(["departments", page, size], fetchDepartments, {
    retry: 3,
    retryDelay: 5000,
  });

  const updateItemMutation = useMutation(updateDepartment, {
    onSuccess: () => {
      notification.success({
        message: "Edit Successful",
        description: "Edit department successful",
        duration: 2,
      });
      queryClient.invalidateQueries("departments");
    },
    onError: (err) => {
      console.error("Error update", err);
      notification.error({
        message: "Edit Failed",
        description: err.response.data.message,
        duration: 2,
      });
    },
  });

  const addItemMutation = useMutation(addDepartment, {
    onSuccess: () => {
      notification.success({
        message: "Add Successful",
        description: "Add department successful",
        duration: 2,
      });
      queryClient.invalidateQueries("departments");
    },
    onError: (err) => {
      console.error("Error add", err);
      notification.error({
        message: "Add Failed",
        description: err.response.data.message,
        duration: 2,
      });
    },
  });

  // const deleteItemMutation = useMutation(deleteDepartment, {
  //   onSuccess: () => {
  //     notification.success({
  //       message: "Delete Successful",
  //       description: "Delete employee successful",
  //       duration: 2,
  //     });
  //     queryClient.invalidateQueries("departments");
  //   },
  //   onError: (err) => {
  //     console.error("Error delete", err);
  //     notification.error({
  //       message: "Delete Failed",
  //       description: "Delete employee failed",
  //       duration: 2,
  //     });
  //   },
  // });

  const updateItem = async (updatedItem) => {
    await updateItemMutation.mutateAsync({ updatedItem });
  };

  const addItem = async (addItem) => {
    await addItemMutation.mutateAsync(addItem);
  };

  // const deleteItem = async (itemId) => {
  //   await deleteItemMutation.mutateAsync(itemId);
  // };

  return {
    departments,
    fetchDepartments,
    updateItem,
    addItem,
    // deleteItem,
    totalCount,
    isFetching,
  };
};

export default useDepartmentStore;
