import React, { Component } from 'react';
import { Layout, Menu, Icon, Empty } from 'antd';
import { Link } from 'react-router-dom';

const { Sider } = Layout;
class Sidebar extends Component {

    renderSubMenu() {
        return this.props.lstSubMenu.map(item => {
            if(item.enable === true){
                return (
                    <Menu.Item key={item.functionID}>
                        <Link to={item.url}>
                            <Icon type={item.iconType} />
                            <span>{item.functionName}</span>
                        </Link>
                    </Menu.Item>
                )
            } else {
                return '';
            }
        })
    }

    render() {
        return (
            <Sider
                className="pg_slider"
                style={{ background: '#fff' }}
                trigger={null}
                collapsible
                collapsed={this.props.collapsed}
            >
                <div className="logo" />
                {(this.props.lstSubMenu.length > 0) ?
                    <Menu
                        className="left_menu"
                        onClick={this.props.handleClickMenu}
                        theme="light"
                        mode="inline"
                        defaultSelectedKeys={['1']}>
                        {this.renderSubMenu()}
                    </Menu>
                    : <Empty className="empty_menu" image="https://gw.alipayobjects.com/mdn/miniapp_social/afts/img/A*pevERLJC9v0AAAAAAAAAAABjAQAAAQ/original" />}

            </Sider>
        );
    }
}

export default Sidebar;