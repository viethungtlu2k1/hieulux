import ConstantList from "./appConfig";
export const navigations = [
  {
    name: "Dashboard.dashboard",
    icon: "home",
    path: ConstantList.ROOT_PATH + "dashboard/analytics",
    isVisible: true,
  },
  {
    name: "Thông tin thu thập",
    path: ConstantList.ROOT_PATH + "directory/jobType",
    icon: "done_outline",
    isVisible: true,
  },
  {
    name: "Chiến dịch",
    path: ConstantList.ROOT_PATH + "directory/campaign",
    icon: "fact_check",
    isVisible: true,
  },
  {
    name: "Số liệu thống kê",
    path: ConstantList.ROOT_PATH + "directory/jobType",
    icon: "trending_up",
    isVisible: true,
  },
  {
    name: "Sản phẩm QC",
    path: ConstantList.ROOT_PATH + "directory/jobType",
    icon: "local_offer",
    isVisible: true,
  },
  {
    name: "Quản trị",
    isVisible: true,
    icon: "approval",
    children: [
      {
        name: "Loại sản phẩm",
        isVisible: true,
        path: ConstantList.ROOT_PATH + "directory/category",
        icon: "keyboard_arrow_right"
      },
      {
        name: "Sản phẩm",
        path: ConstantList.ROOT_PATH + "directory/product",
        icon: "keyboard_arrow_right",
        isVisible: true,
      },
      {
        name: "Không gian Quảng cáo",
        path: ConstantList.ROOT_PATH + "directory/space",
        icon: "keyboard_arrow_right",
        isVisible: true,
      },
      {
        name: "Kênh Quảng cáo",
        path: ConstantList.ROOT_PATH + "directory/channel",
        icon: "keyboard_arrow_right",
        isVisible: true,
      },
      {
        name: "Quốc gia",
        path: ConstantList.ROOT_PATH + "directory/nation",
        icon: "keyboard_arrow_right",
        isVisible: true,
      },
      {
        name: "Tiền tệ",
        path: ConstantList.ROOT_PATH + "directory/currency",
        icon: "keyboard_arrow_right",
        isVisible: true,
      },
      {
        name: "Loại chiến dịch",
        isVisible: true,
        path: ConstantList.ROOT_PATH + "directory/campaignType",
        icon: "keyboard_arrow_right"
      }
    ]
  },
  {
    name: "Dashboard.manage",
    isVisible: true,
    icon: "engineering",
    children: [
      {
        name: "Dashboard.eQAActivityLog",
        isVisible: true,
        path: ConstantList.ROOT_PATH + "user_manager/activity_log",
        icon: "keyboard_arrow_right"
      },
      {
        name: "manage.user",
        isVisible: true,
        path: ConstantList.ROOT_PATH + "user_manager/user",
        icon: "keyboard_arrow_right"
      },
      {
        name: "manage.menu",
        isVisible: true,
        path: ConstantList.ROOT_PATH + "list/menu",
        icon: "keyboard_arrow_right"
      }
    ]
  }
];
