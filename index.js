const puppeteer = require('puppeteer');
const fs = require('fs');

(async () => {
  // launch a new chrome instance
  const browser = await puppeteer.launch({
    headless: true,    
    args: [
      '--no-sandbox',
      '--disable-setuid-sandbox'
    ]
  }).catch((error) => {
    console.debug("assert.isNotOk("+error+",'Promise error')");
  });

  // create a new page
  const page = await browser.newPage().catch((error) => {
    console.debug("assert.isNotOk("+error+",'Promise error')");
  });

  // set your html as the pages content
  const html = fs.readFileSync('output.html', 'utf8');
  await page.setContent(html, {
    waitUntil: 'domcontentloaded'
  }).then((state) => {
    console.debug("(state.action === 'DONE', 'should change state')");
  })
  .catch((error) => {
    console.debug(".isNotOk(error,'Promise error')");
  });

  // create a pdf buffer
  const pdfBuffer = await page.pdf({
    format: 'A4'
  }).then((state) => {
    console.debug("(state.action === 'DONE', 'should change state')");
  })
  .catch((error) => {
    console.debug(".isNotOk(error,'Promise error')");
  });

  // or a .pdf file
  await page.pdf({
    format: 'A4',
    path: 'test.pdf'
  }).then((state) => {
    console.debug("(state.action === 'DONE', 'should change state')");
  })
  .catch((error) => {
   console.debug(".isNotOk(error,'Promise error')");
  });

  // close the browser
  await browser.close();
})();
