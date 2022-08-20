import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const Agency = EgretLoadable({
  loader: () => import("./CampaignType")
});
const ViewComponent = withTranslation()(Agency);
const CampaignTypeRoutesRoutes = [
  {
    path: ConstantList.ROOT_PATH + "directory/campaignType",
    exact: true,
    component: ViewComponent
  }
];

export default CampaignTypeRoutesRoutes;