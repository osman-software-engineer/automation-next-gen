package org.osmanacademy.managers;

import com.microsoft.playwright.*;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.net.UnknownHostException;
import java.util.Properties;

public class PlaywrightInstanceManager implements AutoCloseable {
    private final Logger logger = LoggerFactory.getLogger(PlaywrightInstanceManager.class);
    private  Playwright playwright;
    private  Browser browser;
    private  BrowserContext browserContext;
    private Page page;
    private final ChromiumLaunchOptionsManager chromiumLaunchOptionsManager;

    public PlaywrightInstanceManager(Properties properties) {
        this.chromiumLaunchOptionsManager = new ChromiumLaunchOptionsManager(properties);
    }

    public void openChromeWebBrowser() {
        this.chromiumLaunchOptionsManager.manageHeadless();
        this.chromiumLaunchOptionsManager.manageStartMaximized();
        this.playwright = Playwright.create();
        BrowserType chromium = this.playwright.chromium();
        this.browser = chromium.launch(this.chromiumLaunchOptionsManager.getChromiumLaunchOptions());
        this.browserContext = this.browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        this.page = this.browserContext.newPage();
    }

    public Playwright getPlaywright() {
        ObjectUtils.requireNonEmpty(this.playwright,"Playwright Object Cannot be Null");
        return playwright;
    }

    public Page getPage() {
        ObjectUtils.requireNonEmpty(this.playwright,"Playwright Object Cannot be Null");
        return page;
    }

    /**
     * Closes this resource, relinquishing any underlying resources.
     * This method is invoked automatically on objects managed by the
     * {@code try}-with-resources statement.
     *
     * @throws Exception if this resource cannot be closed
     * @apiNote While this interface method is declared to throw {@code
     * Exception}, implementers are <em>strongly</em> encouraged to
     * declare concrete implementations of the {@code close} method to
     * throw more specific exceptions, or to throw no exception at all
     * if the close operation cannot fail.
     *
     * <p> Cases where the close operation may fail require careful
     * attention by implementers. It is strongly advised to relinquish
     * the underlying resources and to internally <em>mark</em> the
     * resource as closed, prior to throwing the exception. The {@code
     * close} method is unlikely to be invoked more than once and so
     * this ensures that the resources are released in a timely manner.
     * Furthermore it reduces problems that could arise when the resource
     * wraps, or is wrapped, by another resource.
     *
     * <p><em>Implementers of this interface are also strongly advised
     * to not have the {@code close} method throw {@link
     * InterruptedException}.</em>
     * <p>
     * This exception interacts with a thread's interrupted status,
     * and runtime misbehavior is likely to occur if an {@code
     * InterruptedException} is {@linkplain Throwable#addSuppressed
     * suppressed}.
     * <p>
     * More generally, if it would cause problems for an
     * exception to be suppressed, the {@code AutoCloseable.close}
     * method should not throw it.
     *
     * <p>Note that unlike the {@link Closeable#close close}
     * method of {@link Closeable}, this {@code close} method
     * is <em>not</em> required to be idempotent.  In other words,
     * calling this {@code close} method more than once may have some
     * visible side effect, unlike {@code Closeable.close} which is
     * required to have no effect if called more than once.
     * <p>
     * However, implementers of this interface are strongly encouraged
     * to make their {@code close} methods idempotent.
     */
    @Override
    public void close() throws Exception {
        try {
            if (page != null) {
                page.close();
                logger.info("Page closed successfully.");
            }
            if (browserContext != null) {
                browserContext.close();
                logger.info("BrowserContext closed successfully.");
            }
            if (browser != null) {
                browser.close();
                logger.info("Browser closed successfully.");
            }
            if (playwright != null) {
                playwright.close();
                logger.info("Playwright instance closed successfully.");
            }
        } catch (Exception e) {
            logger.error("Failed to close Playwright resources: ", e);
        }
    }
}
