import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const Agency = EgretLoadable({
  loader: () => import("./Space")
});
const ViewComponent = withTranslation()(Agency);
const SpaceRoutes = [
  {
    path: ConstantList.ROOT_PATH + "directory/space",
    exact: true,
    component: ViewComponent
  }
];

export default SpaceRoutes;