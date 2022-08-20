import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const nation = EgretLoadable({
  loader: () => import("./Nation")
});
const ViewComponent = withTranslation()(nation);
const NationRoutesRoutes = [
  {
    path: ConstantList.ROOT_PATH +"directory/nation",
    exact: true,
    component: ViewComponent
  }
];

export default NationRoutesRoutes;