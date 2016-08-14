package com.oleksiisosevych.flickr;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static android.support.test.InstrumentationRegistry.getTargetContext;

/**
 * Test rule that provides a {@link MockAppModule} before each test, and clears the
 * {@link App} component after each test automatically
 */
public class TestAppComponentRule implements TestRule {
    private MockAppModule mockAppModule;
    private MockDataModule mockDataModule;
    private MockFickrSearchResultModule mockFickrSearchResultModule;

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                TestApp app = (TestApp) getTargetContext().getApplicationContext();
                try {
                    mockAppModule = new MockAppModule(app);
                    mockDataModule = new MockDataModule();
                    mockFickrSearchResultModule = new MockFickrSearchResultModule();
                    app.setOverrideFlickrAppModule(mockAppModule);
                    app.setOverrideDataModule(mockDataModule);
                    app.setOverrideFlickrSearchResultModule(mockFickrSearchResultModule);
                    base.evaluate();
                } finally {
                    TestApp.clearAppComponent(app);
                }
            }
        };
    }

    public MockAppModule getMockAppModule() {
        return mockAppModule;
    }

    public MockDataModule getMockDataModule() {
        return mockDataModule;
    }

    public MockFickrSearchResultModule getMockFickrSearchResultModule() {
        return mockFickrSearchResultModule;
    }
}
