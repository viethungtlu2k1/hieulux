import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const Agency = EgretLoadable({
  loader: () => import("./Campaign")
});
const ViewComponent = withTranslation()(Agency);
const CampaignRoutesRoutes = [
  {
    path: ConstantList.ROOT_PATH + "directory/campaign",
    exact: true,
    component: ViewComponent
  }
];

export default CampaignRoutesRoutes;