import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const Product = EgretLoadable({
  loader: () => import("./Product")
});
const ViewComponent = withTranslation()(Product);

const RoleRoutes = [
  {
    path:  ConstantList.ROOT_PATH+"directory/product",
    exact: true,
    component: ViewComponent
  }
];

export default RoleRoutes;
