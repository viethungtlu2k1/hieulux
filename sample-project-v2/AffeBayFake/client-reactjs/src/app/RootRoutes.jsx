import React from "react";
import { Redirect } from "react-router-dom";
import homeRoutes from "./views/home/HomeRoutes";
import sessionRoutes from "./views/sessions/SessionRoutes";
import dashboardRoutes from "./views/dashboard/DashboardRoutes";
import administrativeUnitRoutes from "./views/AdministrativeUnit/AdministrativeUnitRoutes"; 
import UserRoutes from "./views/User/UserRoutes"; 
import ConstantList from "./appConfig";
// import MenuRoutes from "./views/Menus/MenuRoutes";
import pageLayoutRoutes from "./views/page-layouts/PageLayoutRoutees";  
import CategoryRoutes from "./views/Category/CategoryRoutes";
import NationRoutes from "./views/Nation/NationRoutes";
import HolidayRoutes from "./views/Holiday/HolidayRoutes"; 
import CalendarRoutes from "./views/calendar/CalendarRoutes";  
import MenuRoutes from "./views/Menus/MenuRoutes"; 
import CustomerRoutes from "./views/Customer/CustomerRoutes"
import CampaignTypeRoutes from "./views/CampaignType/CampaignTypeRoutes";
import CampaignRoutes from "./views/Campaign/CampaignRoutes";
import SpaceRoutes from "./views/Space/SpaceRoutes";
import ChannelAdsRoutes from "./views/ChannelAds/ChannelAdsRoutes";
import CurrencyRoutes from "./views/currency/CurrencyRoutes";

import EventRoutes from "./views/Event/EventRoutes"; 

const redirectRoute = [
  {
    path: ConstantList.ROOT_PATH,
    exact: true,
    component: () => <Redirect to={ConstantList.HOME_PAGE} /> //Luôn trỏ về HomePage được khai báo trong appConfig
  }
];

const errorRoute = [
  {
    component: () => <Redirect to={ConstantList.ROOT_PATH + "session/404"} />
  }
];

const routes = [
  ...homeRoutes,
  ...sessionRoutes,
  ...dashboardRoutes,
  ...CalendarRoutes,
  ...NationRoutes,
  ...administrativeUnitRoutes, 
  ...pageLayoutRoutes,  
  ...CategoryRoutes,
  ...CampaignTypeRoutes, 
  ...CampaignRoutes,
  ...HolidayRoutes, 
  ...MenuRoutes,
  ...UserRoutes, 
  ...CustomerRoutes,
  ...SpaceRoutes,
  ...CurrencyRoutes,
  ...ChannelAdsRoutes,
  ...EventRoutes,  
];

export default routes;
