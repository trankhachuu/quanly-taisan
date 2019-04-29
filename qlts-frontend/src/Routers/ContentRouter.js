import React from 'react';
import { Switch, Route } from "react-router-dom";
import Users from '../Components/Pages/Users';
import Function from '../Components/Pages/Function';
import Group from '../Components/Pages/Group';
import NhanVien from '../Components/Pages/NhanVien';
import Chucvu from '../Components/Pages/Chucvu';
import TinhTrang from '../Components/Pages/TinhTrang';
import Donvitinh from '../Components/Pages/Donvitinh';
import Permission from '../Components/Pages/Permission';
import PhongBan from '../Components/Pages/PhongBan';
import DeviceRequirement from '../Components/Pages/DeviceRequirement/DeviceRequirement';
import SeeDetailedList from '../Components/Pages/DeviceRequirement/SeeDetailedList';
import NhapKho from '../Components/Pages/NhapKho/NhapKho';
import DefaultPage from '../Components/Dashboard/DefaultPage';
import NoMatch from '../Components/ErrorPage/NoMatch';
import AuthorizedRoute from './AuthorizedRoute';
import ListBienNhan from '../Components/Pages/NhapKho/ListBienNhan';
import Bangiao from '../Components/Pages/bangiao/kcbangiao';
import bangiao1 from '../Components/Pages/bangiao/htbangiao';
import RotationType from '../Components/Pages/RotationPage/RotationType';
import ListRotationType from '../Components/Pages/RotationPage/ListRotationType';
// import  Module  from '../Components/Pages/Module';

export default () => (
    <Switch>
        <Route exact path="/" component={DefaultPage} />
        <AuthorizedRoute exact path="/app/user" component={Users} />
        {/* <Route exact path="/app/module" component={Module} /> */}
        <AuthorizedRoute exact path="/app/function" component={Function} />
        <AuthorizedRoute exact path="/app/group" component={Group} />
        <AuthorizedRoute exact path="/app/employee" component={NhanVien} />
        <AuthorizedRoute exact path="/app/phongban" component={PhongBan} />
        <AuthorizedRoute exact path="/app/chucvu" component={Chucvu} />
        <AuthorizedRoute exact path="/app/tinhtrang" component={TinhTrang} />
        <AuthorizedRoute exact path="/app/donvitinh" component={Donvitinh} />
        <AuthorizedRoute exact path="/app/permission/:id" component={Permission} />
        <AuthorizedRoute exact path="/app/yeucauthietbi" component={DeviceRequirement} />
        <AuthorizedRoute exact path="/app/seedetailslist" component={SeeDetailedList} />
        <AuthorizedRoute exact path="/app/nhapkho" component={NhapKho} />
        <AuthorizedRoute exact path="/app/danhsachnhap" component={ListBienNhan} />
        <AuthorizedRoute exact path="/app/error404" component={NoMatch} />
        <AuthorizedRoute exact path="/app/kcbangiao" component={Bangiao} />
        <AuthorizedRoute exact path="/app/htbangiao" component={bangiao1} />
        <AuthorizedRoute exact path="/app/rotationtype" component={RotationType} />
        <AuthorizedRoute exact path="/app/listrotationtype" component={ListRotationType} />
        <Route component={NoMatch} />
    </Switch>
)