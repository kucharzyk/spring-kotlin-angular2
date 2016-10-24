import { browser, element, by } from 'protractor';
import { ShardisUiPage } from './app.po';

describe('shardis-ui App', function() {
  let page: ShardisUiPage;

  beforeEach(() => {
    page = new ShardisUiPage();
    page.navigateTo();
  });

  it('should have a title', () => {
    let subject = browser.getTitle();
    let result = 'Spring Angular 2 starter';
    expect(subject).toEqual(result);
  });

  it('should have main-content', () => {
    let subject = page.getMainContent().isPresent();
    let result = true;
    expect(subject).toEqual(result);
  });

  it('should have navigation', () => {
    let subject = page.getNavigation().isPresent();
    let result = true;
    expect(subject).toEqual(result);
  });

  it('should have footer', () => {
    let subject = page.getFooter().getText();
    let result = 'Spring Boot Angular 2 Webpack Starter';
    expect(subject).toEqual(result);
  });

});
