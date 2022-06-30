<?php

namespace PhpOffice\PhpSpreadsheet\Calculation\Information;

use PhpOffice\PhpSpreadsheet\Calculation\ArrayEnabled;

class ExcelError
{
    use ArrayEnabled;

    /**
     * List of error codes.
     *
     * @var array<string, string>
     */
    public static $errorCodes = [
        'null' => '#NULL!',
        'divisionbyzero' => '#DIV/0!',
        'value' => '#VALUE!',
        'reference' => '#REF!',
        'name' => '#NAME?',
        'num' => '#NUM!',
        'na' => '#N/A',
        'gettingdata' => '#GETTING_DATA',
        'spill' => '#SPILL!',
    ];

    /**
     * @param mixed $value
     */
    public static function throwError($value): string
    {
        return in_array($value, self::$errorCodes, true) ? $value : self::$errorCodes['value'];
    }

    /**
     * ERROR_TYPE.
     *
     * @param mixed $value Value to check
     *
     * @return array|int|string
     */
    public static function type($value = '')
    {
        if (is_array($value)) {
            return self::evaluateSingleArgumentArray([self::class, __FUNCTION__], $value);
        }

        $i = 1;
        foreach (self::$errorCodes as $errorCode) {
            if ($value === $errorCode) {
                return $i;
            }
            ++$i;
        }

        if ($value === self::CALC()) {
            return 14;
        }

        return self::NA();
    }

    /**
     * NULL.
     *
     * Returns the error value #NULL!
     *
     * @return string #NULL!
     */
    public static function null(): string
    {
        return self::$errorCodes['null'];
    }

    /**
     * NaN.
     *
     * Returns the error value #NUM!
     *
     * @return string #NUM!
     */
    public static function NAN(): string
    {
        return self::$errorCodes['num'];
    }

    /**
     * REF.
     *
     * Returns the error value #REF!
     *
     * @return string #REF!
     */
    public static function REF(): string
    {
        return self::$errorCodes['reference'];
    }

    /**
     * NA.
     *
     * Excel Function:
     *        =NA()
     *
     * Returns the error value #N/A
     *        #N/A is the error value that means "no value is available."
     *
     * @return string #N/A!
     */
    public static function NA(): string
    {
        return self::$errorCodes['na'];
    }

    /**
     * VALUE.
     *
     * Returns the error value #VALUE!
     *
     * @return string #VALUE!
     */
    public static function VALUE(): string
    {
        return self::$errorCodes['value'];
    }

    /**
     * NAME.
     *
     * Returns the error value #NAME?
     *
     * @return string #NAME?
     */
    public static function NAME(): string
    {
        return self::$errorCodes['name'];
    }

    /**
     * DIV0.
     *
     * @return string #DIV/0!
     */
    public static function DIV0(): string
    {
        return self::$errorCodes['divisionbyzero'];
    }

    /**
     * CALC.
     *
     * @return string #CALC!
     */
    public static function CALC(): string
    {
        return '#CALC!';
    }
}
