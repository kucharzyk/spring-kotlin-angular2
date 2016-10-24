import { browser, element, by } from 'protractor';

export class ShardisUiPage {
  navigateTo() {
    return browser.get('/');
  }

  getMainContent() {
    return element(by.css('shardis-app .main-content'));
  }

  getNavigation() {
    return element(by.css('shardis-app .navigation'));
  }

  getFooter() {
    return element(by.css('shardis-app .footer md-card-title'));
  }
}
