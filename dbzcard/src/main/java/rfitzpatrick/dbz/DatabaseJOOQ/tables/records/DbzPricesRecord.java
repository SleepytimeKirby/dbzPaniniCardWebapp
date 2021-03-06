/**
 * This class is generated by jOOQ
 */
package rfitzpatrick.dbz.DatabaseJOOQ.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;
import rfitzpatrick.dbz.DatabaseJOOQ.tables.DbzPrices;

import javax.annotation.Generated;


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
public class DbzPricesRecord extends UpdatableRecordImpl<DbzPricesRecord> implements Record9<Integer, Double, String, Double, String, Double, String, Double, String> {

	private static final long serialVersionUID = -1501243485;

	/**
	 * Setter for <code>dbz.dbz_prices.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>dbz.dbz_prices.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>dbz.dbz_prices.priceark</code>.
	 */
	public void setPriceark(Double value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>dbz.dbz_prices.priceark</code>.
	 */
	public Double getPriceark() {
		return (Double) getValue(1);
	}

	/**
	 * Setter for <code>dbz.dbz_prices.urlark</code>.
	 */
	public void setUrlark(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>dbz.dbz_prices.urlark</code>.
	 */
	public String getUrlark() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>dbz.dbz_prices.pricecfl</code>.
	 */
	public void setPricecfl(Double value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>dbz.dbz_prices.pricecfl</code>.
	 */
	public Double getPricecfl() {
		return (Double) getValue(3);
	}

	/**
	 * Setter for <code>dbz.dbz_prices.urlcfl</code>.
	 */
	public void setUrlcfl(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>dbz.dbz_prices.urlcfl</code>.
	 */
	public String getUrlcfl() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>dbz.dbz_prices.pricedbx</code>.
	 */
	public void setPricedbx(Double value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>dbz.dbz_prices.pricedbx</code>.
	 */
	public Double getPricedbx() {
		return (Double) getValue(5);
	}

	/**
	 * Setter for <code>dbz.dbz_prices.urldbx</code>.
	 */
	public void setUrldbx(String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>dbz.dbz_prices.urldbx</code>.
	 */
	public String getUrldbx() {
		return (String) getValue(6);
	}

	/**
	 * Setter for <code>dbz.dbz_prices.pricedbzoutpost</code>.
	 */
	public void setPricedbzoutpost(Double value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>dbz.dbz_prices.pricedbzoutpost</code>.
	 */
	public Double getPricedbzoutpost() {
		return (Double) getValue(7);
	}

	/**
	 * Setter for <code>dbz.dbz_prices.urldbzoutpost</code>.
	 */
	public void setUrldbzoutpost(String value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>dbz.dbz_prices.urldbzoutpost</code>.
	 */
	public String getUrldbzoutpost() {
		return (String) getValue(8);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record9 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row9<Integer, Double, String, Double, String, Double, String, Double, String> fieldsRow() {
		return (Row9) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row9<Integer, Double, String, Double, String, Double, String, Double, String> valuesRow() {
		return (Row9) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return DbzPrices.DBZ_PRICES.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Double> field2() {
		return DbzPrices.DBZ_PRICES.PRICEARK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return DbzPrices.DBZ_PRICES.URLARK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Double> field4() {
		return DbzPrices.DBZ_PRICES.PRICECFL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return DbzPrices.DBZ_PRICES.URLCFL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Double> field6() {
		return DbzPrices.DBZ_PRICES.PRICEDBX;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field7() {
		return DbzPrices.DBZ_PRICES.URLDBX;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Double> field8() {
		return DbzPrices.DBZ_PRICES.PRICEDBZOUTPOST;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field9() {
		return DbzPrices.DBZ_PRICES.URLDBZOUTPOST;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double value2() {
		return getPriceark();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getUrlark();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double value4() {
		return getPricecfl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getUrlcfl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double value6() {
		return getPricedbx();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value7() {
		return getUrldbx();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double value8() {
		return getPricedbzoutpost();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value9() {
		return getUrldbzoutpost();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DbzPricesRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DbzPricesRecord value2(Double value) {
		setPriceark(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DbzPricesRecord value3(String value) {
		setUrlark(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DbzPricesRecord value4(Double value) {
		setPricecfl(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DbzPricesRecord value5(String value) {
		setUrlcfl(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DbzPricesRecord value6(Double value) {
		setPricedbx(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DbzPricesRecord value7(String value) {
		setUrldbx(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DbzPricesRecord value8(Double value) {
		setPricedbzoutpost(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DbzPricesRecord value9(String value) {
		setUrldbzoutpost(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DbzPricesRecord values(Integer value1, Double value2, String value3, Double value4, String value5, Double value6, String value7, Double value8, String value9) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		value8(value8);
		value9(value9);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached DbzPricesRecord
	 */
	public DbzPricesRecord() {
		super(DbzPrices.DBZ_PRICES);
	}

	/**
	 * Create a detached, initialised DbzPricesRecord
	 */
	public DbzPricesRecord(Integer id, Double priceark, String urlark, Double pricecfl, String urlcfl, Double pricedbx, String urldbx, Double pricedbzoutpost, String urldbzoutpost) {
		super(DbzPrices.DBZ_PRICES);

		setValue(0, id);
		setValue(1, priceark);
		setValue(2, urlark);
		setValue(3, pricecfl);
		setValue(4, urlcfl);
		setValue(5, pricedbx);
		setValue(6, urldbx);
		setValue(7, pricedbzoutpost);
		setValue(8, urldbzoutpost);
	}
}
