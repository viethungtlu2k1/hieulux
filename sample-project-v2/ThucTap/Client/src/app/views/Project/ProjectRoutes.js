import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const Agency = EgretLoadable({
  loader: () => import("./Project")
});
const ViewComponent = withTranslation()(Agency);
const ProjectRoutesRoutes = [
  {
    path: ConstantList.ROOT_PATH + "directory/project",
    exact: true,
    component: ViewComponent
  }
];

export default ProjectRoutesRoutes;