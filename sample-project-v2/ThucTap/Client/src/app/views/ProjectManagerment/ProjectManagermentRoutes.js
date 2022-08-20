import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const Agency = EgretLoadable({
  loader: () => import("./ProjectManagerment")
});
const ViewComponent = withTranslation()(Agency);
const ProjectManagermentRoutesRoutes = [
  {
    path: ConstantList.ROOT_PATH + "directory/ProjectManagerment",
    exact: true,
    component: ViewComponent
  }
];

export default ProjectManagermentRoutesRoutes;