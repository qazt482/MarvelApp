package com.example.rakesh.marvelapp.data;


import com.example.rakesh.marvelapp.model.Comic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.Assert.assertEquals;

public class ComicsRepositoryImplTest {

    private MockWebServer server;
    private ComicsRepositoryImpl comicsRepository;

    @Before
    public void startServer() throws IOException {
        server = new MockWebServer();
        server.start();
        String url = server.url("/").toString();
        comicsRepository = new ComicsRepositoryImpl(url);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void mapsJsonToPojo() {

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(getJson()));

        TestObserver<Comic> testObserver = new TestObserver<>();
        comicsRepository.getComics()
                .subscribeWith(testObserver);

        testObserver.assertNoErrors();
        assertEquals(2, testObserver.values().size());
        assertEquals("Astonishing X-Men Vol. 9 (Trade Paperback)", testObserver.values().get(0).getTitle());
        assertEquals(true, testObserver.values().get(0).getDescription().startsWith("Cyclops &mdash; destroyer of worlds?"));
        assertEquals(112, testObserver.values().get(0).getPageCount());
        assertEquals(0, testObserver.values().get(0).getPrice().compareTo(new BigDecimal(16.99)));
        assertEquals(4, testObserver.values().get(0).getCreators().size());
        assertEquals("Warren Ellis", testObserver.values().get(0).getCreators().get(0));
        assertEquals("http://i.annihil.us/u/prod/marvel/i/mg/6/c0/4ffb20969054f/standard_medium.jpg", testObserver.values().get(0).getImageUrl());
    }

    private String getJson() {
        return "{\n" +
                "  \"code\": 200,\n" +
                "  \"status\": \"Ok\",\n" +
                "  \"copyright\": \"© 2017 MARVEL\",\n" +
                "  \"attributionText\": \"Data provided by Marvel. © 2017 MARVEL\",\n" +
                "  \"attributionHTML\": \"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2017 MARVEL</a>\",\n" +
                "  \"etag\": \"9f0fd939cf6412ba3b30b0d909476198b9ea6ebd\",\n" +
                "  \"data\": {\n" +
                "    \"offset\": 0,\n" +
                "    \"limit\": 20,\n" +
                "    \"total\": 40048,\n" +
                "    \"count\": 20,\n" +
                "    \"results\": [\n" +
                "      {\n" +
                "        \"id\": 40952,\n" +
                "        \"digitalId\": 0,\n" +
                "        \"title\": \"Astonishing X-Men Vol. 9 (Trade Paperback)\",\n" +
                "        \"issueNumber\": 0,\n" +
                "        \"variantDescription\": \"\",\n" +
                "        \"description\": \"Cyclops &mdash; destroyer of worlds? After presiding over a mutant schism as leader of the X-Men, it looks like a visit from Storm may make Cyclops himself again &mdash; but it quickly becomes apparent that Storm is not herself, either. She&rsquo;s from another reality &mdash; one in which Cyclops soon finds himself a prisoner. And the key to his survival rests in his fellow captives, although they aren&rsquo;t the X-Men he knows. Alongside General James Howlett, Cyclops will battle this world&rsquo;s so-called &ldquo;Savior&rdquo; to save himself and countless other mutants. But there&rsquo;s a reason Savior has been gathering and sacrificing mutants from other worlds. And if Cyclops is to liberate himself and his strange new friends, it may mean dooming this planet and all its inhabitants! Collecting ASTONISHING X-MEN (2004) #44-47 and material from ASTONISHING X-MEN: GHOST BOXES #1.\",\n" +
                "        \"modified\": \"2012-10-19T15:43:53-0400\",\n" +
                "        \"isbn\": \"978-0-7851-6178-3\",\n" +
                "        \"upc\": \"\",\n" +
                "        \"diamondCode\": \"AUG120723\",\n" +
                "        \"ean\": \"9780785 161783 51699\",\n" +
                "        \"issn\": \"\",\n" +
                "        \"format\": \"Trade Paperback\",\n" +
                "        \"pageCount\": 112,\n" +
                "        \"textObjects\": [\n" +
                "          {\n" +
                "            \"type\": \"issue_solicit_text\",\n" +
                "            \"language\": \"en-us\",\n" +
                "            \"text\": \"Cyclops &mdash; destroyer of worlds? After presiding over a mutant schism as leader of the X-Men, it looks like a visit from Storm may make Cyclops himself again &mdash; but it quickly becomes apparent that Storm is not herself, either. She&rsquo;s from another reality &mdash; one in which Cyclops soon finds himself a prisoner. And the key to his survival rests in his fellow captives, although they aren&rsquo;t the X-Men he knows. Alongside General James Howlett, Cyclops will battle this world&rsquo;s so-called &ldquo;Savior&rdquo; to save himself and countless other mutants. But there&rsquo;s a reason Savior has been gathering and sacrificing mutants from other worlds. And if Cyclops is to liberate himself and his strange new friends, it may mean dooming this planet and all its inhabitants! Collecting ASTONISHING X-MEN (2004) #44-47 and material from ASTONISHING X-MEN: GHOST BOXES #1.\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/40952\",\n" +
                "        \"urls\": [\n" +
                "          {\n" +
                "            \"type\": \"detail\",\n" +
                "            \"url\": \"http://marvel.com/comics/collection/40952/astonishing_x-men_vol_9_trade_paperback?utm_campaign=apiRef&utm_source=61f014953d81d22410a93ddf6714586e\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"purchase\",\n" +
                "            \"url\": \"http://comicstore.marvel.com/Astonishing-X-Men-Vol-9-0/digital-comic/32607?utm_campaign=apiRef&utm_source=61f014953d81d22410a93ddf6714586e\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"series\": {\n" +
                "          \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/15167\",\n" +
                "          \"name\": \"Astonishing X-Men Vol. 9 (2011 - Present)\"\n" +
                "        },\n" +
                "        \"variants\": [],\n" +
                "        \"collections\": [],\n" +
                "        \"collectedIssues\": [],\n" +
                "        \"dates\": [\n" +
                "          {\n" +
                "            \"type\": \"onsaleDate\",\n" +
                "            \"date\": \"2012-10-24T00:00:00-0400\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"focDate\",\n" +
                "            \"date\": \"2012-10-09T00:00:00-0400\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"prices\": [\n" +
                "          {\n" +
                "            \"type\": \"printPrice\",\n" +
                "            \"price\": 16.99\n" +
                "          }\n" +
                "        ],\n" +
                "        \"thumbnail\": {\n" +
                "          \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/6/c0/4ffb20969054f\",\n" +
                "          \"extension\": \"jpg\"\n" +
                "        },\n" +
                "        \"images\": [\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/6/c0/4ffb20969054f\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"creators\": {\n" +
                "          \"available\": 4,\n" +
                "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/40952/creators\",\n" +
                "          \"items\": [\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/creators/676\",\n" +
                "              \"name\": \"Warren Ellis\",\n" +
                "              \"role\": \"writer\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/creators/536\",\n" +
                "              \"name\": \"Greg Pak\",\n" +
                "              \"role\": \"writer\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/creators/436\",\n" +
                "              \"name\": \"Adi Granov\",\n" +
                "              \"role\": \"artist\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/creators/9137\",\n" +
                "              \"name\": \"Mike McKone\",\n" +
                "              \"role\": \"penciller (cover)\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"returned\": 4\n" +
                "        },\n" +
                "        \"characters\": {\n" +
                "          \"available\": 0,\n" +
                "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/40952/characters\",\n" +
                "          \"items\": [],\n" +
                "          \"returned\": 0\n" +
                "        },\n" +
                "        \"stories\": {\n" +
                "          \"available\": 2,\n" +
                "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/40952/stories\",\n" +
                "          \"items\": [\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92726\",\n" +
                "              \"name\": \"Cover #92726\",\n" +
                "              \"type\": \"cover\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92727\",\n" +
                "              \"name\": \"Interior #92727\",\n" +
                "              \"type\": \"interiorStory\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"returned\": 2\n" +
                "        },\n" +
                "        \"events\": {\n" +
                "          \"available\": 0,\n" +
                "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/40952/events\",\n" +
                "          \"items\": [],\n" +
                "          \"returned\": 0\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 1347,\n" +
                "        \"digitalId\": 0,\n" +
                "        \"title\": \"Wolverine Vol. 3: Return of the Native (Trade Paperback)\",\n" +
                "        \"issueNumber\": 0,\n" +
                "        \"variantDescription\": \"\",\n" +
                "        \"description\": \"Wolverine must delve deep into his enigmatic past when a mysterious mutant known only as the Native returns to his life.  But who is she, and is she friend or foe?  And why might she hold the key to unlock the secrets of Wolverine's past?  Guest-starring Sabretooth!  \\r\\nCollects WOLVERINE #12-19.\\r\\n\",\n" +
                "        \"modified\": \"2013-07-26T11:24:32-0400\",\n" +
                "        \"isbn\": \"0-7851-1397-5\",\n" +
                "        \"upc\": \"5960611397-00111\",\n" +
                "        \"diamondCode\": \"\",\n" +
                "        \"ean\": \"\",\n" +
                "        \"issn\": \"\",\n" +
                "        \"format\": \"Trade Paperback\",\n" +
                "        \"pageCount\": 0,\n" +
                "        \"textObjects\": [\n" +
                "          {\n" +
                "            \"type\": \"issue_solicit_text\",\n" +
                "            \"language\": \"en-us\",\n" +
                "            \"text\": \"Wolverine must delve deep into his enigmatic past when a mysterious mutant known only as the Native returns to his life.  But who is she, and is she friend or foe?  And why might she hold the key to unlock the secrets of Wolverine's past?  Guest-starring Sabretooth!  \\r\\nCollects WOLVERINE #12-19.\\r\\n\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/1347\",\n" +
                "        \"urls\": [\n" +
                "          {\n" +
                "            \"type\": \"detail\",\n" +
                "            \"url\": \"http://marvel.com/comics/collection/1347/wolverine_vol_3_return_of_the_native_trade_paperback?utm_campaign=apiRef&utm_source=61f014953d81d22410a93ddf6714586e\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"series\": {\n" +
                "          \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/1216\",\n" +
                "          \"name\": \"Wolverine Vol. 3: Return of the Native (2004)\"\n" +
                "        },\n" +
                "        \"variants\": [],\n" +
                "        \"collections\": [],\n" +
                "        \"collectedIssues\": [\n" +
                "          {\n" +
                "            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/828\",\n" +
                "            \"name\": \"Wolverine (2003) #19\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/760\",\n" +
                "            \"name\": \"Wolverine (2003) #18\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/527\",\n" +
                "            \"name\": \"Wolverine (2003) #17\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/718\",\n" +
                "            \"name\": \"Wolverine (2003) #16\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/658\",\n" +
                "            \"name\": \"Wolverine (2003) #15\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/490\",\n" +
                "            \"name\": \"Wolverine (2003) #14\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/464\",\n" +
                "            \"name\": \"Wolverine (2003) #13\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/427\",\n" +
                "            \"name\": \"Wolverine (2003) #12\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"dates\": [\n" +
                "          {\n" +
                "            \"type\": \"onsaleDate\",\n" +
                "            \"date\": \"2004-10-13T00:00:00-0400\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"focDate\",\n" +
                "            \"date\": \"-0001-11-30T00:00:00-0500\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"prices\": [\n" +
                "          {\n" +
                "            \"type\": \"printPrice\",\n" +
                "            \"price\": 9.99\n" +
                "          }\n" +
                "        ],\n" +
                "        \"thumbnail\": {\n" +
                "          \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/3/30/4bc6642b76143\",\n" +
                "          \"extension\": \"jpg\"\n" +
                "        },\n" +
                "        \"images\": [\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/f/f0/51ed631433911\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/3/60/51ed630491a95\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/9/e0/51ed62fc7538f\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/b/d0/51ed62ee00b6c\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/9/c0/51ed62d676cc7\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/a/00/51ed62cc88f75\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/c/40/51ed62b5caf92\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/f/20/4bc697cadbe12\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/c/e0/4bc6948dd9ad3\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/c/60/4bc692ce6b54b\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/d/00/4bc68f9713cef\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/6/80/4bc68636e812a\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/9/80/4bc68155ed4e3\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/a/10/4bc67ec49c387\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/b/b0/4bc679bad1a02\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/3/30/4bc6642b76143\",\n" +
                "            \"extension\": \"jpg\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"creators\": {\n" +
                "          \"available\": 7,\n" +
                "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/1347/creators\",\n" +
                "          \"items\": [\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/creators/638\",\n" +
                "              \"name\": \"Edgar Clement\",\n" +
                "              \"role\": \"colorist\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/creators/589\",\n" +
                "              \"name\": \"Nelson DeCastro\",\n" +
                "              \"role\": \"inker\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/creators/527\",\n" +
                "              \"name\": \"Tom Palmer\",\n" +
                "              \"role\": \"inker\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/creators/5052\",\n" +
                "              \"name\": \"Jimmy Palmiotti\",\n" +
                "              \"role\": \"inker\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/creators/49\",\n" +
                "              \"name\": \"Darick Robertson\",\n" +
                "              \"role\": \"penciller (cover)\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/creators/28\",\n" +
                "              \"name\": \"Greg Rucka\",\n" +
                "              \"role\": \"writer\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/creators/410\",\n" +
                "              \"name\": \"Rus Wooton\",\n" +
                "              \"role\": \"letterer\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"returned\": 7\n" +
                "        },\n" +
                "        \"characters\": {\n" +
                "          \"available\": 2,\n" +
                "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/1347/characters\",\n" +
                "          \"items\": [\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1009368\",\n" +
                "              \"name\": \"Iron Man\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1009718\",\n" +
                "              \"name\": \"Wolverine\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"returned\": 2\n" +
                "        },\n" +
                "        \"stories\": {\n" +
                "          \"available\": 18,\n" +
                "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/1347/stories\",\n" +
                "          \"items\": [\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2166\",\n" +
                "              \"name\": \"Cover #2166\",\n" +
                "              \"type\": \"cover\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2167\",\n" +
                "              \"name\": \"Interior #2167\",\n" +
                "              \"type\": \"interiorStory\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2168\",\n" +
                "              \"name\": \"Cover #2168\",\n" +
                "              \"type\": \"cover\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2169\",\n" +
                "              \"name\": \"Interior #2169\",\n" +
                "              \"type\": \"interiorStory\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2170\",\n" +
                "              \"name\": \"Cover #2170\",\n" +
                "              \"type\": \"cover\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2171\",\n" +
                "              \"name\": \"Interior #2171\",\n" +
                "              \"type\": \"interiorStory\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2172\",\n" +
                "              \"name\": \"Cover #2172\",\n" +
                "              \"type\": \"cover\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2173\",\n" +
                "              \"name\": \"Interior #2173\",\n" +
                "              \"type\": \"interiorStory\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2174\",\n" +
                "              \"name\": \"Cover #2174\",\n" +
                "              \"type\": \"cover\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2175\",\n" +
                "              \"name\": \"Interior #2175\",\n" +
                "              \"type\": \"interiorStory\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2176\",\n" +
                "              \"name\": \"Cover #2176\",\n" +
                "              \"type\": \"cover\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2177\",\n" +
                "              \"name\": \"Interior #2177\",\n" +
                "              \"type\": \"interiorStory\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2178\",\n" +
                "              \"name\": \"Cover #2178\",\n" +
                "              \"type\": \"cover\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2179\",\n" +
                "              \"name\": \"Interior #2179\",\n" +
                "              \"type\": \"interiorStory\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2180\",\n" +
                "              \"name\": \"\\\"RETURN OF THE NATIVE\\\" 7 (OF 7) The finale to Greg Rucka’s acclaimed run on Wolverine, featuring a guest appearance by Nightcraw\",\n" +
                "              \"type\": \"cover\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/2181\",\n" +
                "              \"name\": \"\\\"RETURN OF THE NATIVE\\\" 7 (OF 7) The finale to Greg Rucka’s acclaimed run on Wolverine, featuring a guest appearance by Nightcraw\",\n" +
                "              \"type\": \"interiorStory\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/6409\",\n" +
                "              \"name\": \"WOLVERINE 12-19\",\n" +
                "              \"type\": \"cover\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/6410\",\n" +
                "              \"name\": \"WOLVERINE 12-19\",\n" +
                "              \"type\": \"interiorStory\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"returned\": 18\n" +
                "        },\n" +
                "        \"events\": {\n" +
                "          \"available\": 0,\n" +
                "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/1347/events\",\n" +
                "          \"items\": [],\n" +
                "          \"returned\": 0\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }

}