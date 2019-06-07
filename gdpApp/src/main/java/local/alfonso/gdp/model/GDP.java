package local.alfonso.gdp.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

public class GDP {
    private static final Logger logger = LoggerFactory.getLogger(GDP.class);

    private static final AtomicLong counter = new AtomicLong();

    private long id;
    private String name;
    private long gdp;

    public GDP(String name, String gdp) {
        this.id = counter.incrementAndGet();
        this.name = name;
        this.gdp = Long.parseLong(gdp);
    }

    public GDP(String name, long gdp)
    {
        this.id = counter.incrementAndGet();
        this.name = name;
        this.gdp = gdp;
    }

    public GDP(GDP toClone)
    {
        this.id = counter.incrementAndGet();
        this.name = toClone.getName();
        this.gdp = toClone.getGdp();
    }

    public GDP() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGdp() {
        return gdp;
    }

    public void setGdp(long gdp) {
        this.gdp = gdp;
    }

    @Override
    public String toString() {
        return "GDP{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gdp=" + gdp +
                '}';
    }
}
