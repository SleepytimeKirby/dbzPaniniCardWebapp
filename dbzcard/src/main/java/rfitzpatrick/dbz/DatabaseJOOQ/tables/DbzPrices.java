/**
 * This class is generated by jOOQ
 */
package rfitzpatrick.dbz.DatabaseJOOQ.tables;


import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import rfitzpatrick.dbz.DatabaseJOOQ.Dbz;
import rfitzpatrick.dbz.DatabaseJOOQ.Keys;
import rfitzpatrick.dbz.DatabaseJOOQ.tables.records.DbzPricesRecord;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbzPrices extends TableImpl<DbzPricesRecord> {

	private static final long serialVersionUID = 1878992200;

	/**
	 * The reference instance of <code>dbz.dbz_prices</code>
	 */
	public static final DbzPrices DBZ_PRICES = new DbzPrices();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<DbzPricesRecord> getRecordType() {
		return DbzPricesRecord.class;
	}

	/**
	 * The column <code>dbz.dbz_prices.id</code>.
	 */
	public final TableField<DbzPricesRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>dbz.dbz_prices.priceark</code>.
	 */
	public final TableField<DbzPricesRecord, Double> PRICEARK = createField("priceark", org.jooq.impl.SQLDataType.DOUBLE, this, "");

	/**
	 * The column <code>dbz.dbz_prices.urlark</code>.
	 */
	public final TableField<DbzPricesRecord, String> URLARK = createField("urlark", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>dbz.dbz_prices.pricecfl</code>.
	 */
	public final TableField<DbzPricesRecord, Double> PRICECFL = createField("pricecfl", org.jooq.impl.SQLDataType.DOUBLE, this, "");

	/**
	 * The column <code>dbz.dbz_prices.urlcfl</code>.
	 */
	public final TableField<DbzPricesRecord, String> URLCFL = createField("urlcfl", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>dbz.dbz_prices.pricedbx</code>.
	 */
	public final TableField<DbzPricesRecord, Double> PRICEDBX = createField("pricedbx", org.jooq.impl.SQLDataType.DOUBLE, this, "");

	/**
	 * The column <code>dbz.dbz_prices.urldbx</code>.
	 */
	public final TableField<DbzPricesRecord, String> URLDBX = createField("urldbx", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>dbz.dbz_prices.pricedbzoutpost</code>.
	 */
	public final TableField<DbzPricesRecord, Double> PRICEDBZOUTPOST = createField("pricedbzoutpost", org.jooq.impl.SQLDataType.DOUBLE, this, "");

	/**
	 * The column <code>dbz.dbz_prices.urldbzoutpost</code>.
	 */
	public final TableField<DbzPricesRecord, String> URLDBZOUTPOST = createField("urldbzoutpost", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * Create a <code>dbz.dbz_prices</code> table reference
	 */
	public DbzPrices() {
		this("dbz_prices", null);
	}

	/**
	 * Create an aliased <code>dbz.dbz_prices</code> table reference
	 */
	public DbzPrices(String alias) {
		this(alias, DBZ_PRICES);
	}

	private DbzPrices(String alias, Table<DbzPricesRecord> aliased) {
		this(alias, aliased, null);
	}

	private DbzPrices(String alias, Table<DbzPricesRecord> aliased, Field<?>[] parameters) {
		super(alias, Dbz.DBZ, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<DbzPricesRecord> getPrimaryKey() {
		return Keys.KEY_DBZ_PRICES_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<DbzPricesRecord>> getKeys() {
		return Arrays.<UniqueKey<DbzPricesRecord>>asList(Keys.KEY_DBZ_PRICES_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DbzPrices as(String alias) {
		return new DbzPrices(alias, this);
	}

	/**
	 * Rename this table
	 */
	public DbzPrices rename(String name) {
		return new DbzPrices(name, null);
	}
}