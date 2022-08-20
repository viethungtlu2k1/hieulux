import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const Agency = EgretLoadable({
  loader: () => import("./Holiday")
});
const ViewComponent = withTranslation()(Agency);
const HolidayRoutesRoutes = [
  {
    path: ConstantList.ROOT_PATH + "directory/holiday",
    exact: true,
    component: ViewComponent
  }
];

export default HolidayRoutesRoutes;